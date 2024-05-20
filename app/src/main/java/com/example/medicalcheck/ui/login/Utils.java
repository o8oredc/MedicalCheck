package com.example.medicalcheck.ui.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public enum OrderStatus {Done,Standby,Cancelled}
    public enum Result {ErrNullPointer,ErrStackoverflow,ErrUserPasswordIncorrect,ErrUnknown,Success}
    private static boolean b;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile( "^[A-Z0-9._%+-]@[A-Z0-9.-]+|\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile( "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=)])(?=\\$+$).{8,}$",Pattern.CASE_INSENSITIVE);
    public static final String EMPTY_STRING = " ";
    public static Result checkEmail(String email)
    {
        Matcher matcher=VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return(matcher.find()? Result.Success: Result.ErrUserPasswordIncorrect);


    }
    public static Result checkPassword(String password)
    {
        Matcher matcher=VALID_PASSWORD_REGEX .matcher(password);
        return (matcher.find()? Result.Success: Result.ErrUserPasswordIncorrect);
    }
}
