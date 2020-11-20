package artolagallicchio;

public class Permisos {
    
    private boolean[][] permisosProgramas;
    private boolean[][] permisosRecursos;
    private Usuario[] usuarios;
    private Programa[] programas;
    private Recurso[] recursos;

    public Permisos(boolean[][] permisosProgramas, boolean[][] permisosRecursos, Usuario[] usuarios, Programa[] programas, Recurso[] recursos){
        this.permisosProgramas = permisosProgramas;
        this.permisosRecursos = permisosRecursos;
        this.usuarios = usuarios;
        this.programas = programas;
        this.recursos = recursos;
    }
    
    public boolean TienePermisoRecurso(Usuario usuario, Recurso recurso){
        boolean usuarioEncontrado = false;
        boolean recursoEncontrado = false;
        int indiceUsuario = 0;
        int indiceRecurso = 0;
        for (int i = 0; i < this.usuarios.length && usuarioEncontrado; i++) {
            if (this.usuarios[i] == usuario) {
                usuarioEncontrado = true;
                indiceUsuario = i;
            }
        }
        for (int i = 0; i < recursos.length && recursoEncontrado; i++) {
            if (recursos[i] == recurso) {
                recursoEncontrado = true;
                indiceRecurso = i;
            }
        }
        if (usuarioEncontrado && recursoEncontrado) {
            return permisosRecursos[indiceUsuario][indiceRecurso];
        }else{
            return false;
        }       
    }
    
    public boolean TienePermisoPrograma(Usuario usuario, Programa programa){
        boolean usuarioEncontrado = false;
        boolean programaEncontrado = false;
        int indiceUsuario = 0;
        int indicePrograma = 0;
        for (int i = 0; i < usuarios.length && usuarioEncontrado; i++) {
            if (usuarios[i] == usuario) {
                usuarioEncontrado = true;
                indiceUsuario = i;
            }
        }
        for (int i = 0; i < programas.length && programaEncontrado; i++) {
            if (programas[i] == programa) {
                programaEncontrado = true;
                indicePrograma = i;
            }
        }
        if (usuarioEncontrado && programaEncontrado) {
            return permisosProgramas[indiceUsuario][indicePrograma];
        }else{
            return false;
        }       
    }
    
    public boolean SePuedeEjecutar (Usuario usuario,Programa programa, Recurso[] recursos){
        return true;
    }
}
