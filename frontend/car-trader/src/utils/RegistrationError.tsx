type RegistrationErrorTypes =
    | 'Email is already taken'
    | 'Server error';

export class RegistrationError extends Error {
    name: RegistrationErrorTypes;
    message: string;

    constructor({name, message
    }:{ 
        name: RegistrationErrorTypes;
        message: string;
    }) {
        super();
        this.name = name;
        this.message = message;
    };
}
