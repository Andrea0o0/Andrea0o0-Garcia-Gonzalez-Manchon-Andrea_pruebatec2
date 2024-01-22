package com.practicalexercises.assessment2.logic;

import com.practicalexercises.assessment2.logic.Administrator;
import com.practicalexercises.assessment2.logic.Shift;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-01-22T21:59:17")
@StaticMetamodel(ProcedureEntity.class)
public class ProcedureEntity_ { 

    public static volatile SingularAttribute<ProcedureEntity, String> requirements;
    public static volatile SingularAttribute<ProcedureEntity, String> description;
    public static volatile SingularAttribute<ProcedureEntity, Administrator> admin;
    public static volatile ListAttribute<ProcedureEntity, Shift> shifts;
    public static volatile SingularAttribute<ProcedureEntity, Long> id;
    public static volatile SingularAttribute<ProcedureEntity, String> title;

}