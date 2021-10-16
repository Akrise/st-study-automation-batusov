package at.study.redmine.api.rest_assured;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class GsonProvider {

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
            .create();

    public static class  LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime>{

        @Override
        public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return ZonedDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString()).toLocalDateTime();
        }
    }

    public static class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime>{

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(localDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        }
    }
}
