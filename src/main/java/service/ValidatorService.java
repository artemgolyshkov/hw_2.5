package service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.employeebook.exception.InvalidNameException;
import pro.sky.employeebook.exception.InvalidSurnameException;

@Service
public class ValidatorService {

    public String validateName(final String name) {
        if (!StringUtils.isAlpha(name)) {
            throw new InvalidNameException(name);
        }
        return StringUtils.capitalize(name.toLowerCase());
    }

    public String validateSurname(final String surname) {
        // Мамин-Сибиряк
        String[] surnames = surname.split("-");
        for (int i = 0; i < surnames.length; i++) {
            if (!StringUtils.isAlpha(surnames[i])) {
                throw new InvalidSurnameException(surname);
            }
            surnames[i] = StringUtils.capitalize(surnames[i].toLowerCase());
        }
        return String.join("-", surnames);
    }
}