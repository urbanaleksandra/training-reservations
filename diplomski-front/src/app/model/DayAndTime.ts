export class DayAndTime {
    id: number;
    day: string;
    time: string;

    constructor(id?: number, day?: string, time?: string){
        this.id = id;
        this.day = day;
        this.time = time;
    }
}