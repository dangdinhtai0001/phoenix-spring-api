package com.phoenix.base.repository.imp;

import com.phoenix.base.model.querydsl.QFwException;
import com.querydsl.core.types.Path;
import com.querydsl.sql.RelationalPathBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionRepositoryImpTest {
    @Test
    public void testCreateRelationPath() {
        RelationalPathBase<QFwException> relationalPathBase = new QFwException("fw_exception", "phoenix-v2");

        System.out.println(relationalPathBase.getColumns());
    }

}