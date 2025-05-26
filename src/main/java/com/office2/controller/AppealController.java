package com.office2.controller;

import com.google.zxing.NotFoundException;
import com.office2.model.Appeal;
import com.office2.model.User;
import com.office2.service.AppealService;
import com.office2.util.QrCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/appeals")
public class AppealController {

    @Autowired
    private AppealService appealService;

    private static final Map<String,String> LOGIN_TO_DISPLAY = Map.of(
            "admin1", "Власюк Э.Д.",
            "admin2", "Козлов Р.С.",
            "admin3", "Плетнева И.А."
    );

    @GetMapping
    public String list(Model model, HttpSession session) {
        User u = (User) session.getAttribute("loggedUser");
        if (u == null) return "redirect:/login";
        List<Appeal> list = appealService.getByManager(u.getUsername());
        model.addAttribute("appeals", list);
        return "appeal-list";
    }

    @GetMapping("/new")
    public String newForm(HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }
        return "new-appeal";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam("qrFile") MultipartFile qrFile,
            HttpSession session,
            Model model
    ) throws IOException {
        // Проверяем, что пользователь залогинен
        User u = (User) session.getAttribute("loggedUser");
        if (u == null) {
            return "redirect:/login";
        }

        // Проверяем наличие файла
        if (qrFile == null || qrFile.isEmpty()) {
            model.addAttribute("error", "Файл не выбран или пустой");
            return "new-appeal";
        }

        String decodedText;
        try {
            // Попытка декодировать текст из QR
            decodedText = QrCodeUtil.decodeQr(qrFile.getInputStream());
        } catch (NotFoundException nf) {
            model.addAttribute("error", "QR-код не распознан: неверный формат");
            return "new-appeal";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка при чтении QR-кода: " + e.getMessage());
            return "new-appeal";
        }

        // Разбираем и сохраняем
        try {
            appealService.parseAndSave(decodedText);
        } catch (IllegalArgumentException iae) {
            model.addAttribute("error", iae.getMessage());
            return "new-appeal";
        }

        return "redirect:/appeals";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model, HttpSession session) {
        User u = (User) session.getAttribute("loggedUser");
        if (u == null) return "redirect:/login";
        Appeal a = appealService.get(id);
        if (!u.getUsername().equals(a.getManagername())) return "redirect:/appeals";
        model.addAttribute("appeal", a);
        return "edit-appeal";
    }

    @PostMapping("/update")
    public String update(
            @RequestParam Long id,
            @RequestParam String resolution,
            @RequestParam(required = false) String note,
            Model model,
            HttpSession session
    ) throws com.google.zxing.WriterException, IOException {
        User u = (User) session.getAttribute("loggedUser");
        if (u == null) return "redirect:/login";

        Appeal a = appealService.get(id);
        if (!u.getUsername().equals(a.getManagername())) return "redirect:/appeals";

        appealService.updateResolutionAndNote(id, resolution, note);
        a = appealService.get(id);

        String displayMgr = LOGIN_TO_DISPLAY.getOrDefault(a.getManagername(), a.getManagername());

        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(a.getId()).append("\n")
                .append("Заявитель: ").append(a.getApplicantname()).append("\n")
                .append("Менеджер: ").append(displayMgr).append("\n")
                .append("Адрес: ").append(a.getAddress()).append("\n")
                .append("Тема: ").append(a.getTheme()).append("\n")
                .append("Содержание: ").append(a.getContent()).append("\n")
                .append("Резолюция: ").append(a.getResolution()).append("\n")
                .append("Заметка: ").append(a.getNote() == null ? "" : a.getNote());

        String qrText = sb.toString();
        String qr = QrCodeUtil.toBase64(qrText, 200, 200);

        model.addAttribute("appeal", a);
        model.addAttribute("qrData", qr);
        model.addAttribute("appealResolutionText", qrText);

        return "qr-result";
    }
}
