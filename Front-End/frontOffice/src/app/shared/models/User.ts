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
	zipCode!: number;
	city!: string;
	country!: string;
	telNum!: string;
	expired!: boolean;
	dateToUnexired!: Date;
	locked!: boolean;
	codeActivation!: number;
	roles!: string;
}
