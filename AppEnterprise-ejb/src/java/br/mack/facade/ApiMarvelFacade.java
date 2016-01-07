/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mack.facade;

import br.mack.json.Thumbnail;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author brunow
 */
@Stateless
public class ApiMarvelFacade {

    private static final String PUBLIC_KEY = "2bed62c34a0c07caf184bd3c9f6c48b8";

    private static final String PRIVATE_KEY = "6a07b0e715c2f8e5ea81833439cbd2598834a0eb";

    public Thumbnail obterUrlImagem(Integer idPersonagem) throws IOException {
        Thumbnail tn = null;
        long ts = Calendar.getInstance().getTimeInMillis();

        HttpClient cliente = HttpClients.createDefault();
        String stringToHash = ts + PRIVATE_KEY + PUBLIC_KEY;
        String hash = DigestUtils.md5Hex(stringToHash);

        String url = String.format("http://gateway.marvel.com:80/v1/public/characters/%d?ts=%d&apikey=%s&hash=%s",
                idPersonagem, ts, PUBLIC_KEY, hash);
        HttpGet httpget = new HttpGet(url);
        HttpResponse response = null;
        response = cliente.execute(httpget);
        System.out.println(response.getStatusLine());
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream instream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            String result = out.toString();
            Pattern p1 = Pattern.compile("\"thumbnail\":\\{[^{}]+\\}");
            Pattern p2 = Pattern.compile("\\{[^{}]+\\}");
            if (result.contains("thumbnail")) {
                Matcher matcher = p1.matcher(result);
                boolean find = matcher.find();
                if (find) {
                    String g1 = matcher.group();
                    matcher = p2.matcher(g1);
                    find = matcher.find();
                    if (find) {
                        ObjectMapper om = new ObjectMapper();
                        tn = om.readValue(matcher.group(), Thumbnail.class);
                    }
                }
            }
            reader.close();
            instream.close();
        }

        return tn;

    }
}
