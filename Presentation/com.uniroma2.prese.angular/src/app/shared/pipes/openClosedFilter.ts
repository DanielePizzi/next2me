import { Pipe, PipeTransform } from "@angular/core";

@Pipe({name: 'openClosedFilter'})
export class OpenClosedFilter implements PipeTransform {
  transform(value, args:string[]) : any {
    if (value == false) {
      return 'Chiuso';
    } else {
      return 'Aperto';
    }
  }
}
