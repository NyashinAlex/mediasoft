package team.utils;

import net.datafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;

public class GenerationData {

    private static final Faker faker = new Faker();

    public static String getNameProduct() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    public static String getArticle() {
        return faker.internet().uuid();
    }

    public static String getDictionary() {
        return faker.beer().brand();
    }
}
