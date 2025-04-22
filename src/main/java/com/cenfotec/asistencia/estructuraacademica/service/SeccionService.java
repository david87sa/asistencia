package com.cenfotec.asistencia.estructuraacademica.service;

import com.cenfotec.asistencia.estructuraacademica.dto.SeccionDTO;
import com.cenfotec.asistencia.estructuraacademica.entity.Seccion;
import com.cenfotec.asistencia.estructuraacademica.repository.SeccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeccionService {

    private final SeccionRepository seccionRepository;

    public SeccionService(SeccionRepository seccionRepository) {
        this.seccionRepository = seccionRepository;
    }

    public SeccionDTO obtenerSeccionPorId(Long id) {
        Optional<SeccionDTO> dto = seccionRepository.findById(id)
                .map(seccion -> new SeccionDTO(
                        seccion.getId(),
                        seccion.getNombre()
                ));
        if (dto.isEmpty()){
            throw new RuntimeException("Seccion no encontrada");
        }
        return dto.get();
    }
    public List<SeccionDTO> listarTodos(){
        List<Seccion> secciones = seccionRepository.findAll();
        return secciones.stream()
                .map(elemento -> new SeccionDTO(elemento.getId(), elemento.getNombre()))
                .toList();
    }

}

