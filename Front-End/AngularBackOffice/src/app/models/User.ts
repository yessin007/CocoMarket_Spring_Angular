export class User  {
    id!: number;
    name!: string;
    lastName!: string;
    username!: string;
    email!: string;
    password!: string;
    codeReset!: number;
    address!: string;
    dayOfBirth!: Date;
    cin!: string;
    telNum!: string;
    expired!: boolean;
    dateToUnexired!: Date;
    locked!: boolean;
    codeActivation!: number;
    roles!: string;
}