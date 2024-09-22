package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.Category;
import com.platzi.market.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring") // Esta anotacion indica que esta clase va a matear
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"), // Se indica primero el atributo original (categoria), y luego el atributo mapper (category)
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    }) // Para indicar como hacer mi mapp (como quiero traducir mis objetos) se usa esta anotacion, esto tambien indica que vamos a matear varios
    Category toCategory(Categoria categoria); // Vamos a convertir una categoria en category

    @InheritInverseConfiguration // Hace el proceso inverso del @Mappings de arriba
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);
}
