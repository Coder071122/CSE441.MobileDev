<?php

namespace App\Http\Controllers;

use App\Models\Player;
use Illuminate\Http\Request;

class PlayerController extends Controller
{
    public function index() {
        return response()->json(Player::all());
    }
    
    public function store(Request $request) {
        $data = $request->all();
        $data['member_code'] = 'MBR' . str_pad(Player::count() + 1, 3, '0', STR_PAD_LEFT);
        $player = Player::create($data);
        return response()->json($player, 201);
    }
    
    public function show($member_code) {
        return Player::where('member_code', $member_code)->firstOrFail();
    }
    
    public function update(Request $request, $member_code) {
        $player = Player::where('member_code', $member_code)->firstOrFail();
        $player->update($request->all());
        return response()->json($player);
    }
    
    public function destroy($member_code) {
        $player = Player::where('member_code', $member_code)->firstOrFail();
        $player->delete();
        return response()->json(['deleted' => true]);
    }
}
