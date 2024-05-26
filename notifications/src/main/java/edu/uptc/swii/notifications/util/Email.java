package edu.uptc.swii.notifications.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Email {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

    public static String bodyEmail(ZonedDateTime dateTurn, String user, String dependence){
        String body = "<!DOCTYPE html>\n" +
                "<html lang=\"es\"><head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Recordatorio de Cita</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-size: 18px;\n" +
                "            /* Tamaño de letra \n" +
                "            por defecto más grande */\n" +
                "        }\n" +
                "        .container_principal {\n" +
                "            background-image: url('https://st5.depositphotos.com/4218696/67864/i/600/depositphotos_678641230-stock-photo-professional-indian-man-doctor-white.jpg');\n" +
                "            background-size: cover;\n" +
                "            max-width: 700px;\n" +
                "            margin: 0 auto;\n" +
                "            border-radius: 4px;\n" +
                "            padding: 5px;\n" +
                "            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        .container {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-size: cover;\n" +
                "            max-width: 700px;\n" +
                "            margin: 0 auto;\n" +
                "            background-color: rgba(0, 0, 0, 0.4); /* Fondo semitransparente para resaltar el texto */\n" +
                "            border-radius: 8px;\n" +
                "            padding: 20px;\n" +
                "            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);\n" +
                "            color: #ffffff; /* Color de texto */\n" +
                "        }\n" +
                "        h1 {\n" +
                "            font-size: 24px; /* Tamaño de letra más grande para el encabezado */\n" +
                "            color: #ffffff;\n" +
                "            margin-bottom: 20px;\n" +
                "            text-align: center;\n" +
                "            background-color: rgba(0, 0, 0, 0.15); /* Fondo semitransparente para el encabezado */\n" +
                "            padding: 10px;\n" +
                "            border-radius: 8px;\n" +
                "        }\n" +
                "        p {\n" +
                "            font-size: 18px; /* Tamaño de letra más grande para los párrafos */\n" +
                "            color: #ffffff;\n" +
                "            margin-bottom: 20px;\n" +
                "            text-align: justify;\n" +
                "           /* background-color: rgba(0, 0, 0, 0.1); /* Fondo semitransparente para los párrafos */\n" +
                "            padding: 10px;\n" +
                "            border-radius: 8px;\n" +
                "        }\n" +
                "        .name {\n" +
                "            font-weight: bold;\n" +
                "        }\n" +
                "        @media only screen and (max-width: 100px) {\n" +
                "            .container,\n" +
                "            .container_principal {\n" +
                "                width: 100%;\n" +
                "                padding: 10px;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container_principal\">\n" +
                "        <div class=\"container\">\n" +
                "            <h1>Recordatorio de Cita</h1>\n" +
                "            <p><span class=\"salutation\">Hola estimado/a </span><span class=\"name\">" + user.split("\n")[1] + "</span>,</p>\n" +
                "            <p>Recuerda que tienes una cita hoy a las <strong>" + dateTurn.plusMinutes(15).format(formatter) + "</strong> En la dependencia de <strong>" + dependence +"</strong>.</p>\n" +
                "            <p>Por favor, asegúrate de estar listo/a para tu cita a tiempo. Si necesitas cancelar o reprogramar, por favor contáctanos lo antes posible.</p>\n" +
                "            <p>¡Esperamos verte pronto!</p>\n" +
                "        </div>\n" +
                "\n" +
                "    </div>\n" +
                "   \n" +
                "</body>\n" +
                "</html> ";
        return body;
    }

    public static String emailSubject(ZonedDateTime dateTurn){
        return "Tu cita es hoy a las " + dateTurn.plusMinutes(15).format(formatter);
    }
}
