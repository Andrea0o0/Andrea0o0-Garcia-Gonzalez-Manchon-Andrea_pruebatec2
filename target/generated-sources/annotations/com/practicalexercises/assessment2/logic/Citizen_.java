package com.practicalexercises.assessment2.logic;

import com.practicalexercises.assessment2.logic.Shift;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-01-22T21:59:17")
@StaticMetamodel(Citizen.class)
public class Citizen_ extends Person_ {

    public static volatile SingularAttribute<Citizen, String> lastName;
    public static volatile SingularAttribute<Citizen, String> name;
    public static volatile ListAttribute<Citizen, Shift> shifts;
    public static volatile SingularAttribute<Citizen, LocalDate> dateOfBirth;

}