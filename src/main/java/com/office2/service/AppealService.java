package com.office2.service;

import com.office2.dao.AppealDao;
import com.office2.model.Appeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AppealService {

    @Autowired
    private AppealDao appealDao;

    // Шаблон для разбора
    private static final Pattern PARSE_PATTERN = Pattern.compile(
            "Заявитель:\\s*(.*?)\\s+" +
                    "Менеджер:\\s*(.*?)\\s+" +
                    "Адрес:\\s*(.*?)\\s+" +
                    "Тема:\\s*(.*?)\\s+" +
                    "Содержание:\\s*(.*)$"
    );

    // Маппинг отображаемого ФИО -> логин
    private static final Map<String,String> DISPLAY_TO_LOGIN = Map.of(
            "Власюк Э.Д.", "admin1",
            "Козлов Р.С.", "admin2",
            "Плетнева И.А.", "admin3"
    );

    /**
     * Разбирает строку и сохраняет новый Appeal.
     * Менеджер в БД хранится как его логин (admin1/2/3).
     */
    @Transactional
    public void parseAndSave(String line) {
        Matcher m = PARSE_PATTERN.matcher(line.trim());
        if (!m.matches()) {
            throw new IllegalArgumentException("Строка не соответствует формату: " + line);
        }

        String applicant   = m.group(1);
        String displayMgr  = m.group(2);
        String address     = m.group(3);
        String theme       = m.group(4);
        String content     = m.group(5);

        // Мапим ФИО менеджера в его логин
        String loginMgr = DISPLAY_TO_LOGIN.get(displayMgr);
        if (loginMgr == null) {
            throw new IllegalArgumentException("Неизвестный менеджер: " + displayMgr);
        }

        Appeal a = new Appeal();
        a.setApplicantname(applicant);
        a.setManagername(loginMgr);
        a.setAddress(address);
        a.setTheme(theme);
        a.setContent(content);
        a.setResolution(null);
        a.setNote(null);
        a.setStatus(false);

        appealDao.save(a);
    }

    @Transactional(readOnly = true)
    public java.util.List<Appeal> getByManager(String managerLogin) {
        return appealDao.findByManager(managerLogin);
    }

    @Transactional(readOnly = true)
    public Appeal get(Long id) {
        return appealDao.findById(id);
    }

    @Transactional
    public void updateResolutionAndNote(Long id, String resolution, String note) {
        Appeal a = appealDao.findById(id);
        a.setResolution(resolution);
        a.setNote(note);
        a.setStatus(true);
        appealDao.save(a);
    }
}
