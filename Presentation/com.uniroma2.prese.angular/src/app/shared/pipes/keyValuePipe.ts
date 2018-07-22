import { Pipe, PipeTransform } from "@angular/core";

@Pipe({name: 'keyValuePipe'})
export class KeyValuePipe implements PipeTransform {
  transform(value, args:string[]) : any {
    let keys = [];
    value.forEach(element => {
      keys.push({key: Object.keys(element), value: Object.values(element)});
    });
    return keys;
  }
}
