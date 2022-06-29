package com.comdolidoli.demo.validator;

import com.comdolidoli.demo.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Board board = (Board) obj;
        if(StringUtils.isEmpty(board.getTitle())){
            errors.rejectValue("title","key","제목은 2자 이상 30자 이하로 작성해주세요.");
        }
        if(StringUtils.isEmpty(board.getContent())){
            errors.rejectValue("content","key","내용은 1자 이상 500이하로 작성해주세요.");
        }
    }
}
