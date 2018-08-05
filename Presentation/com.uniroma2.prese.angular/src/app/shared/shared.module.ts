import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KeyValuePipe } from './pipes/keyValuePipe';
import { PasswordStrengthBar } from './components/passwordStrengthBar';
import { NgbdModalContentError } from './components/NgbdModalContentError';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    KeyValuePipe,
    PasswordStrengthBar,
    NgbdModalContentError,
  ],
  exports: [
    KeyValuePipe,
    PasswordStrengthBar,
    NgbdModalContentError
  ],
})
export class SharedModule { }
