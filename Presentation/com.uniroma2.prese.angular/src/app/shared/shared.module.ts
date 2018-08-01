import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KeyValuePipe } from './pipes/keyValuePipe';
import { PasswordStrengthBar } from './components/passwordStrengthBar';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    KeyValuePipe,
    PasswordStrengthBar
  ],
  exports: [
    KeyValuePipe,
    PasswordStrengthBar
  ]
})
export class SharedModule { }
