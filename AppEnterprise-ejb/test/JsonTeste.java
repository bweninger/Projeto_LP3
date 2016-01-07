/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.mack.json.Thumbnail;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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
public class JsonTeste {
    
    public JsonTeste() {
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
    public void TesteJson() throws IOException {
        String input = "{\"path\":\"http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b\","
                + "\"extension\":\"jpg\"}";
        
        ObjectMapper om = new ObjectMapper();
        
        Thumbnail tn = om.readValue(input, Thumbnail.class);
        assertEquals(tn.getPath(), "http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b");
        
    }

}
