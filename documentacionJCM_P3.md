# Análisis del método buscarCursoMejorValorado

Tenemos el siguiente código:

```java	
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
```

vamos a suponer que empezamos a contar desde uno para la línea ' Curso mejor = null; '. Entonces nos quedaría este grafo:

```mermaid
flowchart TD
  A([Inicio]) --> B[1.]
  B --> C[2.]
  C --> D[3.]
  C --> K([FIN])
  D --> E[4.]
  E --> F[5.]
  F --> G[6.]
  G --> H[7.]
  E --> J[8.]
  J --> K
  D --> C
  F --> E
  G --> E
  H --> E
```


