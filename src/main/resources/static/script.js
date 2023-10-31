document.addEventListener('DOMContentLoaded', function() {
    // Meminta izin akses ke kamera
    navigator.mediaDevices.getUserMedia({ video: true })
        .then(function (stream) {
            // Menampilkan video kamera di elemen HTML video
            var videoElement = document.getElementById('video-element');
            videoElement.srcObject = stream;
        })
        .catch(function (error) {
            console.error('Gagal mengakses kamera: ' + error);
        });
});
