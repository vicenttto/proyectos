<?php

header('Content-Type: application/json; charset=utf-8');

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    http_response_code(405);
    echo json_encode(['ok' => false, 'msg' => 'Método no permitido.']);
    exit;
}

$raw = file_get_contents('php://input');
$data = json_decode($raw, true);

if (!$data) {
    http_response_code(400);
    echo json_encode(['ok' => false, 'msg' => 'Datos no válidos.']);
    exit;
}

$nombre = htmlspecialchars(trim($data['nombre'] ?? ''), ENT_QUOTES, 'UTF-8');
$email = filter_var(trim($data['email'] ?? ''), FILTER_SANITIZE_EMAIL);
$asunto = htmlspecialchars(trim($data['asunto'] ?? ''), ENT_QUOTES, 'UTF-8');
$mensaje = htmlspecialchars(trim($data['mensaje'] ?? ''), ENT_QUOTES, 'UTF-8');

if (!$nombre || !$email || !$asunto || !$mensaje) {
    echo json_encode(['ok' => false, 'msg' => 'Todos los campos son obligatorios.']);
    exit;
}

if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    echo json_encode(['ok' => false, 'msg' => 'La dirección de email no es válida.']);
    exit;
}

$destinatario = 'hola@murciadesdesusabor.es';
$asunto_email = '[Web] ' . $asunto;

$headers = 'From: "Murcia desde su Sabor Web" <hola@murciadesdesusabor.es>' . "\r\n";
$headers .= 'Reply-To: ' . $nombre . ' <' . $email . '>' . "\r\n";
$headers .= 'Content-Type: text/plain; charset=UTF-8' . "\r\n";
$headers .= 'X-Mailer: PHP/' . phpversion();

$cuerpo = "Has recibido un nuevo mensaje desde el formulario de contacto de murciadesdesusabor.es\n";
$cuerpo .= "-----------------------------------------------------------\n\n";
$cuerpo .= "Nombre:  $nombre\n";
$cuerpo .= "Email:   $email\n";
$cuerpo .= "Asunto:  $asunto\n\n";
$cuerpo .= "Mensaje:\n$mensaje\n\n";
$cuerpo .= "-----------------------------------------------------------\n";
$cuerpo .= "Mensaje enviado desde murciadesdesusabor.es";

$enviado = mail($destinatario, $asunto_email, $cuerpo, $headers);

if ($enviado) {
    echo json_encode(['ok' => true]);
} else {
    http_response_code(500);
    echo json_encode(['ok' => false, 'msg' => 'No se pudo enviar el mensaje. Por favor, inténtalo de nuevo.']);
}