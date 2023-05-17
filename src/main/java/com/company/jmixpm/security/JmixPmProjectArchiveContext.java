package com.company.jmixpm.security;

import io.jmix.core.accesscontext.SpecificOperationAccessContext;

public class JmixPmProjectArchiveContext extends SpecificOperationAccessContext {

    public static final String NAME = "jmix.pm.project.archive";

    public JmixPmProjectArchiveContext() {
        super(NAME);
    }
}
