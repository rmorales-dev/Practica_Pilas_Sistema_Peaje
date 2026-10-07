# Practica_Pilas_Sistema_Peaje
Ejercicio de practica unicamente de pilas para afianzar conocimiento sobre pilas sin mezclar colas

Sistema de PeajeEnunciado:
Una estación de peaje atiende vehículos en una vía. De cada vehículo se conoce:
Placa (String)
Tipo de vehículo (String: Auto, Camión, Moto)
Categoría de carril (int: 1 = Flypass, 2 = Normal)
Valor de tarifa (double)
Consecutivo de paso (int)

El sistema debe permitir a través de un menú:
1. Encolar vehículo (consecutivo automático y validación continua).   
2. Cobrar / Despachar el siguiente vehículo (atendiendo con prioridad a los carriles rápidos/telepeaje; si no hay, atiende manual).   
3. Consultar el vehículo al frente de la cola (usando peek() o búsqueda prioritaria)
4. Ver todos los vehículos en espera.   
5. Total de dinero recaudado y vehículos atendidos.
6. Salir.
ok