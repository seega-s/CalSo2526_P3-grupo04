package com.formandera.domain.utils;

import com.formandera.domain.models.Curso;
import java.util.ArrayList;
import java.util.List;

public class ListaCursos {

    private List<List<Curso>> listaTematicas;
    
    /**
     * Clase que gestiona una colección de cursos organizada por temáticas.
     * Cada temática se modela como una lista de cursos y todas las temáticas
     * se almacenan en una lista de listas.
     */
    public ListaCursos() {
        listaTematicas = new ArrayList<>();
    }

    /**
     * Añade una nueva temática vacía a la lista de temáticas.
     * 
     * @param tematica nombre de la temática a registrar
     */ 
    public void addTematica(String tematica) {
        listaTematicas.add(new ArrayList<>());
    }

    /**
     * Añade un curso a la lista de temáticas.
     * Si la temática ya existe, inserta el curso en ella.
     * Si no existe, crea una nueva sublista para esa temática y añade el curso.
     * 
     * @param curso curso a añadir
     */
    public void addCurso(Curso curso) {
        boolean encontrada = false;
        for (List<Curso> sublista : listaTematicas) {
            if (!sublista.isEmpty() &&
                sublista.get(0).getTematica().equalsIgnoreCase(curso.getTematica())) {
                sublista.add(curso);
                encontrada = true;
                break;
            }
        }
        if (!encontrada) {
            List<Curso> nueva = new ArrayList<>();
            nueva.add(curso);
            listaTematicas.add(nueva);
        }
    }

    /**
     * Busca los cursos de una temática cuyo nivel sea superior al nivel mínimo indicado.
     * Devuelve la lista de cursos ordenada ascendentemente por valoración.
     * 
     * @param tematica     temática en la que buscar
     * @param nivelMinimo  nivel mínimo requerido
     * @return lista de cursos encontrados y ordenados
     * @throws IllegalStateException    si no hay temáticas registradas
     * @throws IllegalArgumentException si la temática no existe
     */
    public List<Curso> buscarCursosPorNivel(String tematica, int nivelMinimo) {
    	
    	if (listaTematicas.isEmpty()) {
            throw new IllegalStateException("No existen temáticas registradas");
        }

        List<Curso> resultado = new ArrayList<>();
        boolean encontrada = false;

        for (List<Curso> sublista : listaTematicas) {
            if (!sublista.isEmpty() &&
                sublista.get(0).getTematica().equalsIgnoreCase(tematica)) {
            	encontrada = true;
                for (Curso c : sublista) {
                    if (c.getNivel() > nivelMinimo) { 
                        resultado.add(c);
                    }
                }
            }
        }

        if (!encontrada) {
            throw new IllegalArgumentException("La temática " + tematica + " no existe en la lista");
        }
        
        for (int i = 0; i < resultado.size() - 1; i++) {
            for (int j = 0; j < resultado.size() - i - 1; j++) {
                if (resultado.get(j).getValoracion() > resultado.get(j + 1).getValoracion()) {
                    Curso temp = resultado.get(j);
                    resultado.set(j, resultado.get(j + 1));
                    resultado.set(j + 1, temp);
                }
            }
        }
        
        return resultado;
    }

    /**
     * Devuelve los cursos de una temática cuyo nivel sea superior al más alto
     * de los cursos ya realizados por el alumno.
     * 
     * @param realizados lista de cursos ya realizados
     * @param tematica   temática a analizar
     * @return lista de cursos pendientes de esa temática
     */
    public List<Curso> cursosPendientesPorTematica(List<Curso> realizados, String tematica) {
        int maxNivel = 0;

        for (Curso c : realizados) {
            if (c.getTematica().equalsIgnoreCase(tematica)) {
                maxNivel = c.getNivel();
                break; 
            }
        }

        List<Curso> pendientes = new ArrayList<>();
        for (List<Curso> sublista : listaTematicas) {
            if (!sublista.isEmpty() &&
                sublista.get(0).getTematica().equalsIgnoreCase(tematica)) {
                for (Curso c : sublista) {
                    if (c.getNivel() > maxNivel) {
                        pendientes.add(c); 
                    }
                }
            }
        }
        return pendientes;
    }

    /**
     * Busca el curso mejor valorado dentro de una temática y un nivel concretos.
     * 
     * @param tematica temática en la que buscar
     * @param nivel    nivel de los cursos a considerar
     * @return el curso mejor valorado o null si no se encuentra
     */
    public Curso buscarCursoMejorValorado(String tematica, int nivel) {
        Curso mejor = null;

        for (List<Curso> sublista : listaTematicas) {
            if (!sublista.isEmpty() &&
                sublista.get(0).getTematica().equalsIgnoreCase(tematica)) {
                for (Curso c : sublista) {
                    if (c.getNivel() == nivel) {
                        if (mejor == null || c.getValoracion() > mejor.getValoracion()) {
                            mejor = c;
                        }
                    }
                }
                break;
            }
        }

        return mejor;
    }

    /**
     * Método contarCursosPorNivel: contar cuántos cursos existen en una temática a partir de un nivel
     */
    public int contarCursosPorNivel(String tematica, int nivelMinimo) {
        int contador = 0;

        for (List<Curso> sublista : listaTematicas) {
            if (!sublista.isEmpty() &&
                sublista.get(0).getTematica().equalsIgnoreCase(tematica)) {
                for (Curso c : sublista) {
                    if (c.getNivel() >= nivelMinimo) { 
                        contador++;
                    }
                }
            }
        }
        return contador;
    }
}

