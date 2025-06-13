<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class GameMatch extends Model
{
    protected $fillable = [
        'match_code',
        'type',
        'player1',
        'player2',
        'team1',
        'team2',
        'score1',
        'score2',
        'winner',
        'status',
        'datetime'
    ];

    protected $casts = [
        'team1' => 'array',
        'team2' => 'array',
        'datetime' => 'datetime'
    ];

    public function playerOne()
    {
        return $this->belongsTo(Player::class, 'player1', 'member_code');
    }

    public function playerTwo()
    {
        return $this->belongsTo(Player::class, 'player2', 'member_code');
    }

    public function winningPlayer()
    {
        return $this->belongsTo(Player::class, 'winner', 'member_code');
    }
}
