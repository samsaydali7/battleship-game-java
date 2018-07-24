/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonHandling;

import json.JSONObject;

/**
 *
 * @author Samer2
 */
public interface IJHandler {
     public JSONObject getJson();
     public void writeJsonToFile();
}
