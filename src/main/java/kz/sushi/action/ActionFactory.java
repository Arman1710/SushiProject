package kz.sushi.action;

import kz.sushi.action.impl.*;
import kz.sushi.action.impl.pageMoveAction.*;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    public static Map<String, IBasicAction> actionMap = new HashMap<>();
    static {
        actionMap.put("changeLocale", new LocaleAction());
        actionMap.put("productAdd", new ProductAddAction());
        actionMap.put("productRemove", new ProductRemoveAction());
        actionMap.put("registration", new RegistrationAction());
        actionMap.put("logout", new LogoutAction());
        actionMap.put("login", new LoginAction());
        actionMap.put("checkout", new CheckoutAction());
        actionMap.put("showAllOrders", new ShowAllOrdersAction());
        actionMap.put("loginLikeGuest", new LoginLikeGuestAction());
        actionMap.put("hideAllOrders", new HideAllOrdersAction());
        actionMap.put("rollsPage", new RollsPageAction());
        actionMap.put("user-rollsPage", new UserRollsPageAction());
        actionMap.put("setsPage", new SetsPageAction());
        actionMap.put("user-setsPage", new UserSetsPageAction());
        actionMap.put("indexPage", new IndexPageAction());
        actionMap.put("user-indexPage", new UserIndexPageAction());
        actionMap.put("loginPage", new LoginPageAction());
        actionMap.put("registrationPage", new RegistrationPageAction());
        actionMap.put("basketPage", new BasketPageAction());
    }
}
