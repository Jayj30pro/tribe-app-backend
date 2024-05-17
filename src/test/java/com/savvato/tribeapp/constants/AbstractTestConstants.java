package com.savvato.tribeapp.constants;

import com.savvato.tribeapp.controllers.dto.UserRequest;
import com.savvato.tribeapp.dto.UsernameDTO;
import com.savvato.tribeapp.entities.*;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractTestConstants {
    public static long USER1_ID = 1;
    public static long USER2_ID = 732;
    public static long USER3_ID = 3;

    public static String USER1_EMAIL = "user1@email.com";
    public static String USER2_EMAIL = "user2@email.com";
    public static String USER3_EMAIL = "user3@email.com";

    public static String USER1_PHONE = "0035551212"; // starts with 0 to indicate to the code that this is a test
    public static String USER2_PHONE = "0035551213"; // starts with 0 to indicate to the code that this is a test
    public static String USER3_PHONE = "0035551214"; // starts with 0 to indicate to the code that this is a test

    public static String USER1_PASSWORD = "password1";
    public static String USER2_PASSWORD = "password2";
    public static String USER3_PASSWORD = "password3";

    public static int USER_IS_ENABLED = 1;

    public static String USER1_PREFERRED_CONTACT_METHOD = "email";
    public static String USER2_PREFERRED_CONTACT_METHOD = "phone";
    public static String USER3_PREFERRED_CONTACT_METHOD = "phone";

    public static String USER1_NAME = "Fake A. Admin";
    public static String USER2_NAME = "Fake R. User"; // the R stand for Regular
    public static String USER3_NAME = "Fake F. AdminUser"; //the F stands for Full Admin and Account holder

    // Phrase1 and matching word ids
    public static long WORD_TABLE_INITIAL_ID = 100L;
    public static long PHRASE1_ID = 1L;
    public static long PHRASE2_ID = 2L;
    public static long PHRASE3_ID = 3L;

    public Set<UserRole> getUserRoles_AccountHolder() {
        Set<UserRole> rtn = new HashSet<>();
        rtn.add(UserRole.ROLE_ACCOUNTHOLDER);
        return rtn;
    }

    public Set<UserRole> getUserRoles_Admin() {
        Set<UserRole> rtn = new HashSet<>();

        rtn.add(UserRole.ROLE_ADMIN);
        rtn.add(UserRole.ROLE_ACCOUNTHOLDER);

        return rtn;
    }

    public Set<UserRole> getUserRoles_Admin_AccountHolder() {
        Set<UserRole> rtn = new HashSet<>();

        rtn.add(UserRole.ROLE_ADMIN);
        rtn.add(UserRole.ROLE_PHRASEREVIEWER);
        rtn.add(UserRole.ROLE_ACCOUNTHOLDER);

        return rtn;
    }

    public User getUser1() {
        User rtn = new User();

        rtn.setId(USER1_ID);
        rtn.setName(USER1_NAME);
        rtn.setEmail(USER1_EMAIL);
        rtn.setPhone(USER1_PHONE);
        rtn.setPassword(USER1_PASSWORD);
        rtn.setEnabled(USER_IS_ENABLED);
        rtn.setCreated();
        rtn.setLastUpdated();
        rtn.setRoles(getUserRoles_Admin());

        return rtn;
    }

    public User getUser2() {
        User rtn = new User();

        rtn.setId(USER2_ID);
        rtn.setName(USER2_NAME);
        rtn.setEmail(USER2_EMAIL);
        rtn.setPhone(USER2_PHONE);
        rtn.setPassword(USER2_PASSWORD);
        rtn.setEnabled(USER_IS_ENABLED);
        rtn.setCreated();
        rtn.setLastUpdated();
        rtn.setRoles(getUserRoles_Admin());

        return rtn;
    }

    public User getUser3() {
        User rtn = new User();

        rtn.setId(USER3_ID);
        rtn.setName(USER3_NAME);
        rtn.setEmail(USER3_EMAIL);
        rtn.setPhone(USER3_PHONE);
        rtn.setPassword(USER3_PASSWORD);
        rtn.setEnabled(USER_IS_ENABLED);
        rtn.setCreated();
        rtn.setLastUpdated();
        rtn.setRoles(getUserRoles_Admin_AccountHolder());

        return rtn;
    }

    public UsernameDTO getUsernameDTOForUserID(Long userId) {
        if (userId == USER1_ID) {
            return UsernameDTO.builder()
                    .userId(USER1_ID)
                    .username(USER1_NAME)
                    .build();
        } else if (userId == USER2_ID) {
            return UsernameDTO.builder()
                    .userId(USER2_ID)
                    .username(USER2_NAME)
                    .build();
        } else if (userId == USER3_ID) {
            return UsernameDTO.builder()
                    .userId(USER3_ID)
                    .username(USER3_NAME)
                    .build();
        } else {
            return null;
        }
    }

    public UserRequest getUserRequestFor(User user) {
        UserRequest rtn = new UserRequest();

        rtn.id = user.getId();
        rtn.name = user.getName();
        rtn.email = user.getEmail();
        rtn.phone = user.getPhone();
        rtn.password = user.getPassword();

        return rtn;
    }

    public Adverb getTestAdverb1() {
        Adverb testAdverb = new Adverb();
        testAdverb.setId(WORD_TABLE_INITIAL_ID);
        testAdverb.setWord("testAdverb");
        return testAdverb;
    }
    public Verb getTestVerb1() {
        Verb testVerb = new Verb();
        testVerb.setId(WORD_TABLE_INITIAL_ID);
        testVerb.setWord("testVerb");
        return testVerb;
    }
    public Preposition getTestPreposition1() {
        Preposition testPreposition = new Preposition();
        testPreposition.setId(WORD_TABLE_INITIAL_ID);
        testPreposition.setWord("testPreposition");
        return testPreposition;
    }
    public Noun getTestNoun1() {
        Noun testNoun = new Noun();
        testNoun.setId(WORD_TABLE_INITIAL_ID);
        testNoun.setWord("testNoun");
        return testNoun;
    }

    public Phrase getTestPhrase1() {
        Phrase testPhrase = new Phrase();
        testPhrase.setId(PHRASE1_ID);
        testPhrase.setAdverbId(WORD_TABLE_INITIAL_ID);
        testPhrase.setVerbId(WORD_TABLE_INITIAL_ID);
        testPhrase.setPrepositionId(WORD_TABLE_INITIAL_ID);
        testPhrase.setNounId(WORD_TABLE_INITIAL_ID);
        return testPhrase;
    }

    public Phrase getTestPhrase2() {
        Phrase testPhrase = new Phrase();
        testPhrase.setId(PHRASE2_ID);
        testPhrase.setAdverbId(WORD_TABLE_INITIAL_ID);
        testPhrase.setVerbId(WORD_TABLE_INITIAL_ID);
        testPhrase.setPrepositionId(WORD_TABLE_INITIAL_ID);
        testPhrase.setNounId(WORD_TABLE_INITIAL_ID);
        return testPhrase;
    }

    public Phrase getTestPhrase3() {
        Phrase testPhrase = new Phrase();
        testPhrase.setId(PHRASE3_ID);
        testPhrase.setAdverbId(WORD_TABLE_INITIAL_ID);
        testPhrase.setVerbId(WORD_TABLE_INITIAL_ID);
        testPhrase.setPrepositionId(WORD_TABLE_INITIAL_ID);
        testPhrase.setNounId(WORD_TABLE_INITIAL_ID);
        return testPhrase;
    }
}
