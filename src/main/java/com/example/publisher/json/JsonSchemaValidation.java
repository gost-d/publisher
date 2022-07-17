package com.example.publisher.json;


import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class JsonSchemaValidation {

    File schemaFile = new File("src/json-schema.json");
    JSONObject jsonSchema = new JSONObject(new JSONTokener(new FileInputStream(schemaFile)));

    public JsonSchemaValidation() throws FileNotFoundException {
    }

    public void validateJson(String json) throws ValidationException{
            Schema schemaValidator = SchemaLoader.load(jsonSchema);
            JSONObject jsonData = new JSONObject(new JSONTokener(json));
            schemaValidator.validate(jsonData);
    }
}

