<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Str;

class PickleballSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    

public function run()
{
    DB::table('users')->insert([
        ['uid' => 'admin_uid_123', 'display_name' => 'Admin TLU', 'email' => 'admin@tlu.edu.vn', 'role' => 'admin'],
        ['uid' => 'member_uid_456', 'display_name' => 'Nguyen Van A', 'email' => 'vana@gmail.com', 'role' => 'member'],
    ]);

    DB::table('players')->insert([
        ['member_code' => 'MBR001', 'username' => 'Nguyen Van A', 'avatar' => 'mbr001.jpg', 'birthday' => '2005-10-10', 'hometown' => 'Ha Noi', 'residence' => 'TP.HCM', 'rating_single' => 1.25, 'rating_double' => 1.10],
        ['member_code' => 'MBR002', 'username' => 'Tran Thi B', 'avatar' => 'mbr002.jpg', 'birthday' => '2006-03-15', 'hometown' => 'Da Nang', 'residence' => 'TP.HCM', 'rating_single' => 0.95, 'rating_double' => 1.05],
    ]);

    DB::table('game_matches')->insert([
        ['match_code' => 'MATCH001', 'type' => 'single', 'player1' => 'MBR001', 'player2' => 'MBR002', 'score1' => 15, 'score2' => 10, 'winner' => 'MBR001', 'status' => 'completed', 'datetime' => '2024-05-20 09:00:00'],
    ]);

    DB::table('transactions')->insert([
        ['transaction_code' => 'TRX001', 'member_code' => 'MBR002', 'type' => 'penalty', 'amount' => 20000, 'status' => 'unpaid', 'note' => 'Phat thua tran MATCH001'],
        ['transaction_code' => 'TRX002', 'member_code' => 'MBR001', 'type' => 'monthly_fee', 'amount' => 100000, 'status' => 'paid', 'note' => 'Phi sinh hoat thang 5'],
    ]);

    DB::table('news')->insert([
        ['news_code' => 'NEWS001', 'title' => 'Giao lưu đầu mùa', 'content' => 'Ngày 20/5 CLB tổ chức giao lưu tại nhà thi đấu TLU...', 'image' => 'news001.jpg', 'author_uid' => 'admin_uid_123', 'timestamp' => '2024-05-20 12:00:00'],
    ]);
}

}
