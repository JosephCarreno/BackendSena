package com.api.repositorios;

import com.api.entidades.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioChat extends JpaRepository<Chat,Long> {
}
