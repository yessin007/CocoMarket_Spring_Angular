package com.example.coco_spring.Configuration;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.json.JSONWriter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class ProxyTypeAdapter extends TypeAdapter<Proxy> {
    /*
    @Override
    public void write(JSONWriter out, Proxy value) throws IOException {

    }

     */

    @Override
    public void write(JsonWriter jsonWriter, Proxy proxy) throws IOException {
        //out.value(value.type().toString());

    }

    @Override
    public Proxy read(JsonReader in) throws IOException {
        String typeStr = in.nextString();
        Proxy.Type type = Proxy.Type.valueOf(typeStr);
        return new Proxy(type, new InetSocketAddress(0));
    }
}
