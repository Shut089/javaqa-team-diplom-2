package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    Bank bank = new Bank();
    /* Я обнаружил, что тесты проваливаются, поскольку у счетов вида Account from баланс 0. Чтобы наполнить их баланс,
    нужен конструктор в классе Account, а по условиям менять другие классы нельзя. Поэтому я взял конструктор класса
    SavingAccount, поскольку я им занимался, и сделал счета сберегательными с одинаковыми параметрами. Но чтобы счета
    работали корректно и выполняли цели тестирования, мне также пришлось переписать корректный код из класса
    SavingAccount из ветки saving в ветку bank
     */

    @Test // перевод отрицательной суммы
    public void shouldNotTransferNegativeAmount() {
        Account from = new SavingAccount(2_000, 1_000, 10_000, 5);
        Account to = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertEquals(false, bank.transfer(from, to, -1));
    }

    @Test // перевод нулевой суммы
    public void shouldNotTransferZeroAmount() {
        Account from = new SavingAccount(2_000, 1_000, 10_000, 5);
        Account to = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertEquals(false, bank.transfer(from, to, 0));
    }

    @Test // перевод валидной суммы и проверка граничного значения
    public void shouldTransferValidAmount() {
        Account from = new SavingAccount(2_000, 1_000, 10_000, 5);
        Account to = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertEquals(true, bank.transfer(from, to, 1));
    }

    @Test // перевод большей суммы в рамках класса эквивалентности
    public void shouldTransferLargeAmount() {
        Account from = new SavingAccount(2_000_000, 1_000, 10_000_000, 5);
        Account to = new SavingAccount(2_000, 1_000, 100_000_000, 5);

        Assertions.assertEquals(true, bank.transfer(from, to, 1_000_000));
    }

    @Test // попытка списания недоступной суммы
    public void shouldNotTransferNotAvailableAmount() {
        Account from = new SavingAccount(1_000, 1_000, 10_000, 5);
        Account to = new SavingAccount(2_000, 1_000, 10_000, 5);

        Assertions.assertEquals(false, bank.transfer(from, to, 1_000));
        Assertions.assertEquals(1_000, from.getBalance());
        Assertions.assertEquals(2_000, to.getBalance());
    }

    @Test // попытка зачисления недоступной суммы
    public void shouldNotAddNotAvailableAmount() {
        Account from = new SavingAccount(3_000, 1_000, 10_000, 5);
        Account to = new SavingAccount(9_000, 1_000, 10_000, 5);

        Assertions.assertEquals(false, bank.transfer(from, to, 2_000));
        Assertions.assertEquals(3_000, from.getBalance());
        Assertions.assertEquals(9_000, to.getBalance());
    }
}