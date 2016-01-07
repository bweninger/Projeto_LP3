/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brunow
 */
public class RegexTeste {

    public RegexTeste() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testeRegex() {
        String result = "{\"offset\":0,\"limit\":20,\"total\":1,\"count\":1,\"results\":[{\"id\""
                + ":1009610,\"name\":\"Spider-Man\",\"description\":\"Bitten by a radioactive spider,"
                + " high school student Peter Parker gained the speed, strength and powers of a spider."
                + " Adopting the name Spider-Man, Peter hoped to start a career using his new abilities."
                + " Taught that with great power comes great responsibility, Spidey has vowed to use his powers to help people.\",\"modified\":\"2014-06-02T12:35:16-0400\",\"thumbnail\":{\"path\":\"http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b\",\"extension\":\"jpg\"},\"resourceURI\":\"http://gateway.marvel.com/v1/public/characters/1009610\",\"comics\"";
        Pattern p1 = Pattern.compile("\"thumbnail\":\\{[^{}]+\\}");
        Pattern p2 = Pattern.compile("\\{[^{}]+\\}");
        if (result.contains("thumbnail")) {
            Matcher matcher = p1.matcher(result);
            boolean find = matcher.find();
            assertTrue(find);
            String g1 = matcher.group();
            matcher = p2.matcher(g1);
            find = matcher.find();
            assertTrue(find);
            System.out.println(matcher.group());
        }
    }
}
