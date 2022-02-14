class Datos {
   var a,b: [Int]
    init(a:[Int],b:[Int]){
        self.a=a
        self.b=b
    }
}
class Comparador{
    func sonSimilares(dato:Datos) -> Bool{
        var array1 = dato.a.joined(separator: ",")
        var array2 = dato.b.joined(separator: ",")
         if(contieneMismosDatos(dato)){
           print("Son similares: ["+array1+"] , ["+array2+"]")
           return true
         }else{ 
            print("NO son similares: ["+array1+"] , ["+array2+"]")
             return false }
    }
    private func contieneMismosDatos(dato:Datos){
        for a in dato.a {
            var encontrados:Bool = false
            for b in dato.b {
                if(a==b){encontrados=true break}
            }  
            if(!encontrados){
                return false
            } 
        }
        return true
    }
    func caracteresDiferentes(texto:String):Int{
        var segunda:[Character]  = []
        for char in texto {
            var encontrado:Bool = false
            for(ch in segunda){
                if(ch==Char){ encontrado=true break}
            }
            if(!encontrado){segunda.append(char)}
        }
        let cantidad = segunda.length
        let caracteres = String(segunda)
        print("Caracteres diferentes: \(cantidad) = "+ caracteres)
        return cantidad
    }
}
var comp = Comparador()
var arr1:[Int] = [1,2,3]
var arr2:[Int] = [1,2,3]
var dato = Datos(arr1,arr2)
comp.sonSimilares(dato)

dato.a = [1,2,3]
dato.b = [2,1,3]
comp.sonSimilares(dato)

dato.a = [1,2,2]
dato.b = [2,2,1]
comp.sonSimilares(dato)

comp.caracteresDiferentes("cabca")

comp.caracteresDiferentes("marcas")

comp.caracteresDiferentes("digitalcoaster")


