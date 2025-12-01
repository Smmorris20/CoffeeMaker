<?php
// Basic CoffeeMaker PHP App
$action = $_GET['action'] ?? '';

header('Content-Type: application/json');

if ($action === 'brew') {
    $response = [
        'status' => 'success',
        'message' => 'Coffee is brewing! ☕'
    ];
} elseif ($action === 'status') {
    $response = [
        'status' => 'success',
        'message' => 'Coffee maker is ready.'
    ];
} else {
    $response = [
        'status' => 'error',
        'message' => 'No action specified. Try ?action=brew or ?action=status'
    ];
}

echo json_encode($response);
