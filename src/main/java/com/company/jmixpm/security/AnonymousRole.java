package com.company.jmixpm.security;

import com.company.jmixpm.entity.Task;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

import javax.annotation.Nonnull;

@Nonnull
@ResourceRole(name = "Anonymous", code = AnonymousRole.CODE, scope = "UI")
public interface AnonymousRole {

    public static final String CODE = "anonymous";

    @MenuPolicy(menuIds = "Task_.browse")
    @ScreenPolicy(screenIds = {"Task_.browse", "PublicTaskCalendar", "MainScreen", "UserRegistration", "UserActivation"})
    void screens();

    @EntityAttributePolicy(entityClass = Task.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Task.class, actions = EntityPolicyAction.READ)
    void task();
}