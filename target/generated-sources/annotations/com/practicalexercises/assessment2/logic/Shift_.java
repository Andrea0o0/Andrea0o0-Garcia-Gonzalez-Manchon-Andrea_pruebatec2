package com.practicalexercises.assessment2.logic;

import com.practicalexercises.assessment2.logic.Citizen;
import com.practicalexercises.assessment2.logic.ProcedureEntity;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-01-22T21:59:17")
@StaticMetamodel(Shift.class)
public class Shift_ { 

    public static volatile SingularAttribute<Shift, String> additionalInformation;
    public static volatile SingularAttribute<Shift, Citizen> citizen;
    public static volatile SingularAttribute<Shift, Boolean> shiftStatus;
    public static volatile SingularAttribute<Shift, LocalDateTime> dateHour;
    public static volatile SingularAttribute<Shift, Long> id;
    public static volatile SingularAttribute<Shift, ProcedureEntity> procedure;

}