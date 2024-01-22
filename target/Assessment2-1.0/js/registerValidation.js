function calculateAge(birthDate, nowDate) {
    const birth = new Date(birthDate);
    let age = nowDate.getFullYear() - birth.getFullYear();

    if (nowDate.getMonth() < birth.getMonth() || (nowDate.getMonth() === birth.getMonth() && nowDate.getDate() < birth.getDate())) {
        age--;
    }

    return age;
}

function citizenValidateFormandSubmitSignIn(e) {
    console.log('in validate sign in');
    e.preventDefault();
    // Reset error messages in case user correct last errors
    // Para resetear los mensajes en caso de que el usuario los haya corregido
    document.querySelectorAll('.error-message').forEach(function (element) {
        element.style.display = 'none';
    });

    let nameElem = document.getElementById("name");
    let lastNameElem = document.getElementById("lastName");
    let dateBirthElem = document.getElementById("dateOfBirth");
    let usernameElem = document.getElementById("username");
    let passwordElem = document.getElementById("inputPassword");
    let repeatPasswordElem = document.getElementById("repeatPassword");

//    In case that the value don't exist we have to evaluate if it's null
// Hay que procurar comprobar si existe en valor de value

    let name = nameElem ? nameElem.value : "";
    let lastName = lastNameElem ? lastNameElem.value : "";
    let dateBirth = dateBirthElem ? dateBirthElem.value : "";
    let username = usernameElem ? usernameElem.value : "";
    let password = passwordElem ? passwordElem.value : "";
    let repeatPassword = repeatPasswordElem ? repeatPasswordElem.value : "";

    let isValid = true;

    // Validation for name and last name (only letters) and can exists spaces
    // La validación de letras y apellidos solo letras y pueden haber espacios
    // Regex name & last Name
    const nameRegex = /^[a-zA-Z\u00C0-\u00FF\d\s!@#$%^&*()_+]+$/;
    if (!name || !name.match(nameRegex)) {
        document.getElementById("nameError").style.display = 'block';
        isValid = false;
    }

    if (!lastName || !lastName.match(nameRegex)) {
        document.getElementById("lastNameError").style.display = 'block';
        isValid = false;
    }

    // Validation for date of birth: It cannot be older than the current date, it cannot be empty, and it cannot be older than 110 years ago, because it is almost impossible for a person of that age to request an appointment electronically, and has to be older than 16 years old
    // Validación fecha: Esta no puede ser mayor que la fecha actual, tampoco que esté vacia, y que sea mayor que hace 110 años, porque es casi imposible que una persona de esa edad pida turno de forma electronica, y tiene que tener más de 16 años para pedir turno
    let currentDate = new Date();
    let birthDate = new Date(dateBirth);
    const age = calculateAge(birthDate, currentDate);

    if (!dateBirth || isNaN(birthDate) || birthDate > currentDate || currentDate.getFullYear() - birthDate.getFullYear() > 110 || age < 16) {
        document.getElementById("dateOfBirthError").style.display = 'block';
        isValid = false;
    }

    // Validation for username can contain letters, numbers, signs, at least 8 characters)
    // Validación de usuario (puede contener letras, numbers, signos y como mínimo 8 letras)
    // Regex username
    const usernameRegex = /^[a-zA-Z0-9!@#$%^&*()_+-]+$/;
    if (!username || username.length < 8 || !username.match(usernameRegex)) {
        document.getElementById("usernameError").style.display = 'block';
        isValid = false;
    }

    // Validation for password (HAS TO CONTAIN letters (upper and lower case), numbers, signs)
    // Validación de contraseña (tiene que contener letras mayusculas y minus, numero y signos
    // Regex de contraseña
    const passwordRegex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$/;
    
    if (!password || password.length < 8 || !password.match(passwordRegex)) {
        document.getElementById("passwordError").style.display = 'block';
        isValid = false;
    }

    if (password !== repeatPassword) {
        document.getElementById("repeatPasswordError").style.display = 'block';
        isValid = false;
    }

    if (isValid) {
        document.getElementById("myForm").submit(); 
    }
}

function userAndPasswordValidateFormandSubmit(e) {
    e.preventDefault();
    console.log('innnnn user and password validation');
    // Reset error messages in case user correct last errors
    // Para resetear los mensajes en caso de que el usuario los haya corregido
    document.querySelectorAll('.error-message').forEach(function (element) {
        element.style.display = 'none';
    });

    let usernameElem = document.getElementById("username");
    let passwordElem = document.getElementById("inputPassword");

//    In case that the value don't exist we have to evaluate if it's null
// Hay que procurar comprobar si existe en valor de value

    let username = usernameElem ? usernameElem.value : "";
    let password = passwordElem ? passwordElem.value : "";

    let isValid = true;


    // Validation for username can contain letters, numbers, signs, at least 8 characters)
    // Validación de usuario (puede contener letras, numbers, signos y como mínimo 8 letras)
    // Regex username
    const usernameRegex = /^[a-zA-Z0-9!@#$%^&*()_+-]+$/;
    if (!username || username.length < 8 || !username.match(usernameRegex)) {
        document.getElementById("usernameError").style.display = 'block';
        isValid = false;
    }

    // Validation for password (HAS TO CONTAIN letters (upper and lower case), numbers, signs)
    // Validación de contraseña (tiene que contener letras mayusculas y minus, numero y signos
    // Regex de contraseña
    const passwordRegex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$/;
    
    if (!password || password.length < 8 || !password.match(passwordRegex)) {
        document.getElementById("passwordError").style.display = 'block';
        isValid = false;
    }

    if (isValid) {
//        console.log('Form submitted!');
        document.getElementById("myForm").submit(); 
    }
}
    
 
function togglePasswordVisibility(iconPassword,inputElement) {
    const passwordToggle = document.getElementById(iconPassword);
    const input = document.getElementById(inputElement);
    console.log(passwordToggle.className === "fas fa-eye",passwordToggle);
    if (passwordToggle.className === "fas fa-eye") {
        input.type = "password";
        passwordToggle.classList.replace("fa-eye","fa-eye-slash");
    } else {
        input.type = "text";
        passwordToggle.classList.replace("fa-eye-slash","fa-eye");
    }
}


function toggleDropdownAndShowModal() {
    const dropdownMenu = document.getElementById("userDropdown");
    const logoutModal = document.getElementById("logoutModalId");

    dropdownMenu.classList.toggle("show");
    logoutModal.classList.add("show");
    logoutModal.scrollTop = 0;
}