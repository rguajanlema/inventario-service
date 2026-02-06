package com.empresa.inventario.domain.objetosDeValor;

import com.empresa.inventario.domain.exception.DomainException;

import java.util.Objects;
import java.util.regex.Pattern;

public final class CodigoCategoria {

    private static final Pattern FORMATO = Pattern.compile("^C-\\d{4}$");

    private final String value;

    private CodigoCategoria(String value) {
        this.value = value;
    }

    public static CodigoCategoria of(String value) {
        if (value == null || value.isBlank()) {
            throw new DomainException("El código de categoría es obligatorio");
        }
        if (!FORMATO.matcher(value).matches()) {
            throw new DomainException("Formato inválido de código. Ej: C-0001");
        }
        return new CodigoCategoria(value);
    }

    public String value() {
        return value;
    }

    // 🔑 MUY IMPORTANTE para repositorios, maps, equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodigoCategoria)) return false;
        CodigoCategoria that = (CodigoCategoria) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
