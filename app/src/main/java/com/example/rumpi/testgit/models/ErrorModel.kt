package com.example.rumpi.testgit.models

import com.google.gson.JsonElement

class ErrorModel {
    var message: String? = null
    var documentation_url: String? = null
    companion object {
        fun parseFromJson(json: JsonElement): ErrorModel{
            val error = ErrorModel()
            val root = json.asJsonObject
            if(root.has("message")){
                error.message = root.getAsJsonPrimitive("message").asString
            }
            if(root.has("documentation_url")){
                error.documentation_url = root.getAsJsonPrimitive("documentation_url").asString
            }
            return error
        }
    }
}
