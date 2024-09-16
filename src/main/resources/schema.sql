CREATE TABLE IF NOT EXISTS bootcamp (
    id SERIAL PRIMARY KEY,               -- Clave primaria autogenerada
    nombre VARCHAR(50) NOT NULL UNIQUE,  -- El nombre no se puede repetir y es obligatorio
    descripcion VARCHAR(90) NOT NULL     -- La descripción es obligatoria
);

CREATE TABLE IF NOT EXISTS bootcamp_capacidad (
    id SERIAL PRIMARY KEY,              -- Clave primaria simple
    bootcamp_id INTEGER NOT NULL,
    capacidad_id INTEGER NOT NULL,
    UNIQUE (bootcamp_id, capacidad_id), -- Índice único para garantizar la unicidad
    FOREIGN KEY (bootcamp_id) REFERENCES bootcamp(id) ON DELETE CASCADE,
    FOREIGN KEY (capacidad_id) REFERENCES capacidad(id) ON DELETE CASCADE
);
