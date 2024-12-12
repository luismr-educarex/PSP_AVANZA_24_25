import socket

# Configuración del cliente
HOST = 'localhost'  # Dirección del servidor
PORT = 49171        # Puerto del servidor
MENSAJE_A_ENVIAR = 223  # Mensaje que el cliente enviará

# AF_INET -> Protocolo IPv4
# SOCK_STREAM -> Protocolo TCP
with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    try:
        # Conexión al servidor
        s.connect((HOST, PORT))
        print("(Cliente) Conexión establecida.")

        # Enviar el mensaje al servidor
        s.send(MENSAJE_A_ENVIAR.to_bytes(1, 'little'))
        print("(Cliente) Mensaje enviado:", MENSAJE_A_ENVIAR)

        # Recibir respuesta del servidor
        mensaje_recibido = s.recv(1)  # Recibe 1 byte
        mensaje_recibido = int.from_bytes(mensaje_recibido, 'little')
        print("(Cliente) Mensaje recibido:", mensaje_recibido)

    except ConnectionRefusedError:
        print("(Cliente) No se pudo conectar al servidor. Asegúrate de que esté en ejecución.")
    except Exception as e:
        print("(Cliente) Error:", e)
