package kz.sushi.action.impl.pageMoveAction;

import kz.sushi.action.IBasicAction;

import javax.servlet.http.HttpServletRequest;

import static kz.sushi.util.Constant.INDEX_PAGE;

public class IndexPageAction implements IBasicAction {
    @Override
    public String execute(HttpServletRequest request) {
        return INDEX_PAGE;
    }
}
