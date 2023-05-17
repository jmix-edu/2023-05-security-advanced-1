package com.company.jmixpm.screen.mynotifications;

import com.company.jmixpm.app.NotificationService;
import com.company.jmixpm.entity.Notification;
import com.company.jmixpm.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.core.SaveContext;
import io.jmix.core.UnconstrainedDataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.DataGrid;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@UiController("MyNotifications")
@UiDescriptor("my-notifications.xml")
public class MyNotifications extends Screen {

    @Autowired
    private DataGrid<Notification> notificationsTable;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private UnconstrainedDataManager unconstrainedDataManager;
    @Autowired
    private CollectionLoader<Notification> notificationsDl;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private NotificationService notificationService;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        notificationsDl.setParameter("current_user_id", ((User) currentAuthentication.getUser()).getId());
        notificationsDl.load();
    }

    /*@Install(to = "notificationsDl", target = Target.DATA_LOADER)
    private List<Notification> notificationsDlLoadDelegate(LoadContext<Notification> loadContext) {
        return unconstrainedDataManager.loadList(loadContext);
    }

    @Install(target = Target.DATA_CONTEXT)
    private Set<Object> commitDelegate(SaveContext saveContext) {
        return unconstrainedDataManager.save(saveContext);
    }*/

    @Subscribe("notificationsTable.markAsRead")
    public void onNotificationsTableMarkAsRead(Action.ActionPerformedEvent event) {
        // take item from the table
        Notification item = notificationsTable.getSingleSelected();
        if (item == null) {
            return;
        }

        // set isRead
//        item.setIsRead(true);
        // save changes
//        dataManager.save(item);

//        notificationService.maskAsRead(item);
        notificationService.markAsReadEm(item);

        // reload the table
        notificationsDl.load();
    }
}
