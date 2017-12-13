/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codemovers.scholar.engine.api.v1.profile.entities;

/**
 *
 * @author Mover
 */
public enum ProfileTypes {
    INVALID(0, "INVALID", "invalid"),
    STUDENT(1, "STUDENT", "student"),
    USER(2, "USER", "user");

    private final Integer id;
    private final String code;
    private final String value;

    private ProfileTypes(Integer id, String code, String value) {
        this.id = id;
        this.code = code;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static ProfileTypes fromInt(Integer profileId) {

        ProfileTypes profile_type = ProfileTypes.INVALID;

        if (profileId != null) {
            switch (profileId) {
                case 1:
                    profile_type = ProfileTypes.STUDENT;
                    break;
                case 2:
                    profile_type = ProfileTypes.USER;
                    break;

                default:
                    profile_type = INVALID;
                    break;
            }
        }

        return profile_type;


    }

    public static ProfileTypes fromString(String profile_name) {

        ProfileTypes profile_type = ProfileTypes.INVALID;

        if (profile_name != null) {
            switch (profile_name.toUpperCase()) {
                case "STUDENT":
                    profile_type = ProfileTypes.STUDENT;
                    break;
                case "USER":
                    profile_type = ProfileTypes.USER;
                    break;

                default:
                    profile_type = INVALID;
                    break;
            }
        }

        return profile_type;

    }


}
