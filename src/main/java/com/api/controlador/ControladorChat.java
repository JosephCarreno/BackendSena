package com.api.controlador;

import com.api.entidades.Chat;
import com.api.excepciones.ResourseNotFoundException;
import com.api.servicios.ServicioChat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api-chat")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorChat {
    public static final Logger logger = LoggerFactory.getLogger(ControladorChat.class);

    @Autowired
    private ServicioChat servicioChat;


    @GetMapping("/chat")
    public List<Chat> listaChat(){
        List<Chat> listaChats = this.servicioChat.listaChat();
        logger.info("Lista de chat obtenids");
        listaChats.forEach(chat -> logger.info(chat.toString()));
        return listaChats;
    }

    @GetMapping("/chat/{id}")
    public ResponseEntity<Chat> buscarChatPorId(@PathVariable Long id){
        Chat chatEncontrado = this.servicioChat.buscarChatPorId(id);
        if(chatEncontrado != null){
            return ResponseEntity.ok(chatEncontrado);
        }else{
            throw new ResourseNotFoundException("No se encontró el ID");
        }

    }

    @PostMapping("/chat")
    @ResponseStatus(HttpStatus.CREATED)
    public Chat guardarChat(@RequestBody Chat chat){
        logger.info("Chat agregado: {}", chat);
        return this.servicioChat.guardarChat(chat);
    }

    @PutMapping("/Chat/{id}")
    public ResponseEntity<Chat> actualizarChat (
            @PathVariable Long id,
            @RequestBody Chat chat){
        Chat chatEncontrado = this.servicioChat.buscarChatPorId(id);
        if(chatEncontrado != null){

        }else{
            throw new ResourseNotFoundException("Recurso no encontrado:" +id);
        }
        chatEncontrado.setComentario(chat.getComentario());
        this.servicioChat.guardarChat(chatEncontrado);
        return ResponseEntity.ok(chatEncontrado);

    }

    @DeleteMapping("/chat/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarChat(@PathVariable Long id) {
        Chat chat = this.servicioChat.buscarChatPorId(id);
        if (chat == null) {
            throw new ResourseNotFoundException("Id no encontrado: "+id);
        }
        this.servicioChat.eliminarChat(id);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Chat eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
