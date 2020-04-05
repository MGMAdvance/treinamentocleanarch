package br.com.treinamento.cleanarch.configuration.exception.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer status;
    private String msg;
    private Long timestamp;
}