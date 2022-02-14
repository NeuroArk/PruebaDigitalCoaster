package neuroark.appsytutoriales.pruebadigitalcoaster.basededatos

class PerfilRepository (val dao: PerfilDao){
    var perfil: Perfil? = dao.cargarConId()
    fun insertar(perfil: Perfil) {
        return dao.insertar(perfil)
    }
    fun modificar(perfil: Perfil) {
        return dao.modificar(perfil)
    }
    suspend fun eliminar(perfil: Perfil) {
        return dao.eliminar(perfil)
    }
    suspend fun cargarTodos(): List<Perfil> {
        return dao.cargarTodos()
    }

    fun cargar(): Perfil? {
        return dao.cargarConId()
    }
}